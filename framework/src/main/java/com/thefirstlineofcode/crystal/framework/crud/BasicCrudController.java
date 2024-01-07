package com.thefirstlineofcode.crystal.framework.crud;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.thefirstlineofcode.crystal.framework.data.IDataProtocolAdapter;
import com.thefirstlineofcode.crystal.framework.data.IIdProvider;
import com.thefirstlineofcode.crystal.framework.data.ListQueryParams;
import com.thefirstlineofcode.crystal.framework.error.ValidationException;

public abstract class BasicCrudController<ID, T extends IIdProvider<ID>> implements IBasicCrudController<ID, T>, IDataProtocolAdapterAware {
	protected IDataProtocolAdapter dataProtocolAdapter;
	
	@GetMapping
	@Override
	public List<T> getResources(HttpServletRequest request,
			@RequestHeader HttpHeaders httpHeaders,
				@RequestParam Map<String, String> requestParameters,
					HttpServletResponse response) {
		
		if (!isGetResourcesEnabled())
			throw new MethodNotAllowedException();
			
		List<T> list;
		if (dataProtocolAdapter.isGetListRequest(request, httpHeaders, requestParameters)) {
			ListQueryParams listQueryParams = dataProtocolAdapter.parseListQueryParams(request, httpHeaders, requestParameters);
			
			list = doGetList(listQueryParams);
			
			dataProtocolAdapter.prepareListResponse(response, listQueryParams, getService());
		} else if (dataProtocolAdapter.isGetManyRequest(request, httpHeaders, requestParameters)) {
			String[] ids = dataProtocolAdapter.parseManyIds(request, httpHeaders, requestParameters);
			list = doGetMany(ids);
		} else if (dataProtocolAdapter.isGetManyReferenceRequest(request, httpHeaders, requestParameters)) {
			// TODO
			throw new RuntimeException("Not implemented yet!");
		} else {
			// TODO
			throw new RuntimeException("Not implemented yet!");
		}
		
		return list;
	}
	
	protected boolean isGetResourcesEnabled() {
		return true;
	}
	
	private List<T> doGetMany(String[] ids) {
		return getService().getMany(ids);
	}
	
	@GetMapping(value = "/{id}")
	@Override
	public T getResource(@PathVariable("id") ID id) {
		if (!isGetResourceEnabled())
			throw new MethodNotAllowedException();
		
		return getService().getOne(id);
	}
	
	protected boolean isGetResourceEnabled() {
		return true;
	}

	@PutMapping(value = "/{id}")
	@Override
	public T updateResource(@PathVariable("id") ID id, @RequestBody T updated) {
		if (!isUpdateResourceEnabled())
			throw new MethodNotAllowedException();
		
		if (updated.getId() == null)
			updated.setId(id);
		
		if (!updated.getId().equals(id))
			throw new RuntimeException("ID not matched.");
		
		T existed = getService().getOne(updated.getId());
		if (existed == null)
			throw new RuntimeException("Updated domain object isn't existed.");
		
		validUpdated(updated, existed);
		repairUpdated(updated, existed);		
		
		return getService().update(updated);
	}
	
	protected boolean isUpdateResourceEnabled() {
		return true;
	}
	
	protected void validUpdated(T existed, T updated) throws ValidationException {}

	protected <C> C repairUpdated(C updated, C existed) {
		if (updated == null && existed != null)
			return existed;
		
		Class<?> domainClass = existed.getClass();
		Field[] fields = getFields(domainClass);
		for (Field field : fields) {
			try {				
				if (isEmbeddedObject(field)) {
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), domainClass);
					Object updatedEmbeddedObject = propertyDescriptor.getReadMethod().invoke(updated, new Object[0]);
					Object existedEmbeddedObject = propertyDescriptor.getReadMethod().invoke(existed, field);
					
					Object repairedEmbeddedObject = repairUpdated(updatedEmbeddedObject, existedEmbeddedObject);
					
					propertyDescriptor.getWriteMethod().invoke(updated, repairedEmbeddedObject);
					
				} else {					
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), domainClass);
					Object updatedPropertyValue = propertyDescriptor.getReadMethod().invoke(updated, new Object[0]);
					
					if (updatedPropertyValue != null)
						continue;
					
					Object existedPropertyValue = propertyDescriptor.getReadMethod().invoke(existed, new Object[0]);
					
					if (existedPropertyValue != null)
						propertyDescriptor.getWriteMethod().invoke(updated, existedPropertyValue);
				}
			} catch (Exception e) {
				throw new RuntimeException("Failed to repair updated domain object.", e);
			}
		}
		
		return updated;
	}

	private boolean isEmbeddedObject(Field field) {
		if (field.getAnnotation(Embedded.class) == null)
			return false;
		
		Class<?> fieldType = field.getClass();
		if (fieldType.isPrimitive())
			return false;
		
		if (fieldType.getAnnotation(Embeddable.class) == null)
			return false;
		
		return true;
	}

	protected Field[] getFields(Class<?> clazz) {
		List<Field> fields = new ArrayList<>();
		
		Class<?> currentClass = clazz;
		while (currentClass != Object.class) {
			Field[] declaredFields = currentClass.getDeclaredFields();
			for (Field field : declaredFields) {
				PropertyDescriptor propertyDescriptor;
				try {
					propertyDescriptor = new PropertyDescriptor(field.getName(), currentClass);
				} catch (IntrospectionException e) {
					throw new RuntimeException(String.format("Can't create property descriptor. Class name: %s. Field name: %s.", currentClass.getName(), field.getName()), e);
				}
				
				if (propertyDescriptor.getReadMethod() == null || propertyDescriptor.getWriteMethod() == null)
					continue;
				
				fields.add(field);
			}
			
			currentClass = currentClass.getSuperclass();
		}
		
		return fields.toArray(new Field[fields.size()]);
	}

	protected List<T> doGetList(ListQueryParams listQueryParams) {
		return getService().getList(listQueryParams);
	}
	
	@Override
	public void setDataProtocolAdapter(IDataProtocolAdapter dataProtocolAdapter) {
		this.dataProtocolAdapter = dataProtocolAdapter;
	}
	
	@DeleteMapping(value = "/{id}")
	@Override
	public void deleteResource(@PathVariable("id") ID id) {
		if (!isDeleteResourceEnabled())
			throw new MethodNotAllowedException();
		
		getService().deleteById(id);
	}

	protected boolean isDeleteResourceEnabled() {
		return true;
	}
}

import {
  Admin,
  Resource,
  ListGuesser,
  EditGuesser,
  ShowGuesser,
  Layout
} from "react-admin";
import {dataProvider} from "./dataProvider";
import woocommerceDataProvider from "ra-data-woocommerce";
import {TreeMenu} from "@bb-tech/ra-components"

async function fetchUiConfiguration(url, options = {}) {
	if (!options.headers) {
		options.headers = new Headers({Accept: 'application/json'});
	}
	
	const response = await fetch(url, options);
	const uiConfiguration = await response.json();
	
	return uiConfiguration;
}

async function getResources(serviceUrl, options = {}) {
	const uiConfigurationUrl = `${serviceUrl}/ui-configuration`;
	
	const uiConfiguration = await fetchUiConfiguration(uiConfigurationUrl, options);
	const resources = await uiConfiguration.resources;
	
	return resources;
}

export async function fetchCrystalConfiguration(serviceUrl, options = {}) {
	const resources = await getResources(serviceUrl, options);
	const configuration = {
		"serviceUrl": serviceUrl,
		"resources": resources
	};
	
	return configuration;
}

function getResourceConfigurations(resources, applicationPages) {
	const resourceConfigurations = new Array();
	let index = 0;
	resources.map(resource => {
		let options = {
			"label": resource.label
		};
		
		if (resource.menuParent !== undefined)
			options['menuParent'] = resource.menuParent;
		
		let listComponent;
		if (resource.parentMenu) {
			options['isMenuParent'] = true;
		} else {
			if (resource.listComponentName !== undefined) {
				listComponent = applicationPages.get(resource.listComponentName);
			} else {
				listComponent = ListGuesser;
			}
			
			console.log("List Component: ", listComponent);
		}
		
		const resourceConfiguration = {
			"name": resource.name,
			"options": options,
			"list": listComponent
		};
		
		resourceConfigurations[index++] = resourceConfiguration;
	});
	
	return resourceConfigurations;
}

export const CrystalApp = ({configuration, applicationPages}) => {
	/*const dataProvider = woocommerceDataProvider({
		woocommerceUrl: configuration.serviceUrl
	});*/
	
	const resourceConfigurations = getResourceConfigurations(configuration.resources, applicationPages);
	
	return (
		<Admin layout={CrystalLayout} dataProvider={dataProvider}>
			{
				resourceConfigurations.map(resourceConfiguration => (	
					<Resource key={resourceConfiguration.name} name={resourceConfiguration.name} options={resourceConfiguration.options} list={resourceConfiguration.list} />
				))
			}
		</Admin>
	);
}

export const CrystalLayout = (props) => {
	return (<Layout {...props} menu={TreeMenu} />);
}

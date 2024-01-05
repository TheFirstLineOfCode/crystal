import {
  Admin,
  Resource,
  ListGuesser,
  EditGuesser,
  ShowGuesser,
  Layout,
  fetchUtils
} from "react-admin";

import simpleRestProvider from "ra-data-simple-rest";
import {TreeMenu} from "@bb-tech/ra-components"
import polyglotI18nProvider from 'ra-i18n-polyglot'
import en from './ca-language-english';
import cn from './ca-language-chinese';
import chineseMessages from './ca-language-chinese'

const i18nProvider = polyglotI18nProvider(
	locale => {
		if (locale === 'en') {
			return import('./ca-language-english').then(messages => messages.default);
		}
		
		return chineseMessages;
	},
	'cn',
	[
		{ locale: 'cn', name: '中文' },
		{ locale: 'en', name: 'English' }
	],
	{allowMissing: true}
);

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

export async function fetchAdminConfiguration(serviceUrl, options = {}) {
	const resources = await getResources(serviceUrl, options);
	const httpClient = (url, options = {}) => {
		if (!options.headers) {
			options.headers = new Headers({Accept: 'application/json'});
		}
		
		return fetchUtils.fetchJson(url, options);
    }
	
	window.httpClient = httpClient;
	
	const dataProvider = simpleRestProvider(serviceUrl, httpClient);
	
	window.serviceUrl = serviceUrl;
	const configuration = {
		"resources": resources,
		"dataProvider": dataProvider
	};
	
	return configuration;
}

function getResourceConfigurations(resources, applicationViews) {
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
			if (resource.listViewName !== undefined) {
				listComponent = applicationViews.get(resource.listViewName);
			} else {
				listComponent = ListGuesser;
			}
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

export const CrystalAdmin = ({configuration, applicationViews}) => {
	const resourceConfigurations = getResourceConfigurations(configuration.resources, applicationViews);
	
	return (
		<Admin layout={CrystalLayout} dataProvider={configuration.dataProvider} i18nProvider={i18nProvider}>
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

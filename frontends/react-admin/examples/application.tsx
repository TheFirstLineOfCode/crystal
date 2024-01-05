import Button from '@mui/material/Button';
import {fetchUtils} from 'react-admin';
import {getServiceUrl} from './crystalAdmin'
import {AboutView} from './aboutView'
import {TestDataView} from './testDataView'
import {UserListView} from './userListView'

export function customizeDataProvider(dataProvider) {
	dataProvider.loadTestData = function() {
		const url = `${serviceUrl}/test-data`;
		return httpClient(url, {method: 'POST'}).
			then(({json}) => {
				return {data: json};
			}
		);
	};
	
	dataProvider.clearTestData = function() {
		const url = `${serviceUrl}/test-data`;
		return httpClient(url, {method: 'DELETE'}).
			then(({json}) => {
				return {data: json};
			}
		);
	};
}

export function getApplicationViews() {
	const applicationViews = new Map([
		['TestDataView', TestDataView],
		['AboutView', AboutView],
		['UserListView', UserListView],
	]);
	
	return applicationViews;
}

import Button from '@mui/material/Button';
import {fetchUtils} from 'react-admin';
import {getServiceUrl} from './crystalAdmin'
import {AboutView} from './AboutView'
import {TestDataView} from './TestDataView'
import {UserListView} from './UserListView'
import {PostListView} from './PostListView'
import {PostShowView} from './PostShowView'

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
		['PostListView', PostListView],
		['PostShowView', PostShowView]
	]);
	
	return applicationViews;
}

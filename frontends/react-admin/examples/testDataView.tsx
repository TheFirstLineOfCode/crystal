import {useState} from 'react';
import Button from '@mui/material/Button';
import {fetchUtils, useTranslate} from 'react-admin'

export const TestDataView = () => {
	const translate = useTranslate();
	const [totalUsers, setTotalUsers] = useState(-1);
	
	const fetchTotalUsers = () => {
		fetchUtils.fetchJson(`${serviceUrl}/test-data/total-users`).
			then(({json}) => {
				setTotalUsers(json.total_users);
			}).catch(error => {
				setTotalUsers(-1);
				console.log('HTTP call failed. Error message:', error)
			});
	}
	
	const loadTestData = () => {
		alert("Loading data.");
	}
	
	const clearTestData = () => {
		alert("Clearing test data.");
	}
	
	fetchTotalUsers();
	
	if (totalUsers == -1) {
		return (
			<>
				<strong>{translate('TestDataView.totalUsers')}: {totalUsers}.</strong>
				<Button variant="contained" size="medium"
						sx= {{width: 256, padding: 1, margin: 2}}
							disabled>
					{translate('TestDataView.loadTestData')}
				</Button>
			</>
		);
	} else if (totalUsers == 0) {
		return (
			<>
				<strong>{translate('TestDataView.totalUsers')}: {totalUsers}.</strong>
				<Button variant="contained" size="medium"
						sx= {{width: 256, padding: 1, margin: 2}}
							onClick={loadTestData}>
					{translate('TestDataView.loadTestData')}
				</Button>
			</>
		);
	} else {
		return (
			<>
				<strong>{translate('TestDataView.totalUsers')}: {totalUsers}.</strong>
				<Button variant="contained" size="medium"
						sx= {{width: 256, padding: 1, margin: 2}}
							onClick={clearTestData}>
					{translate('TestDataView.clearTestData')}
				</Button>
			</>
		);
	}
	

}

import {CrystalAdmin, fetchAdminConfiguration} from './crystalAdmin'
import {getApplicationViews} from './applicationViews'

const configuration = await fetchAdminConfiguration('http://localhost:8080');
const applicationViews = getApplicationViews();

export const App = () => (
	<CrystalAdmin configuration = {configuration} applicationViews = {applicationViews} />
);


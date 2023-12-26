import {
  Resource
} from "react-admin";
import {getApplicationViews} from './applicationViews'
import {CrystalAdmin, fetchAdminConfiguration} from './crystalAdmin'

const configuration = await fetchAdminConfiguration('http://localhost:8080');
const applicationViews = getApplicationViews();

export const App = () => (
	<CrystalAdmin configuration = {configuration} applicationViews = {applicationViews} />
);


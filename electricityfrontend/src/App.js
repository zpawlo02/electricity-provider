
import './App.css';
import Appbar from "./components/Appbar"
import Customer from "./components/Customer"
import PowerLimitation from "./components/PowerLimitation"
function App() {
  return (
    <div className="App">
        <Customer/>
        <PowerLimitation/>
    </div>
  );
}

export default App;

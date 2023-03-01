import { Routes } from "./routes";
import { BrowserRouter } from "react-router-dom";
import "./sass/global.scss";

function App() {
  return (
    <BrowserRouter>
      <Routes />
    </BrowserRouter>
  );
}

export { App };

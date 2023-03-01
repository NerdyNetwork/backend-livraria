import { Routes } from "./routes";
import { BrowserRouter } from "react-router-dom";
import "./sass/global.scss";

export const App = () => {
  return (
    <BrowserRouter>
      <Routes />
    </BrowserRouter>
  );
}

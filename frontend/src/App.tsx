import { Routes } from "./routes";
import { BrowserRouter } from "react-router-dom";
import "./sass/global.scss";
import { Header } from "./components/Header";

export const App = () => {
  return (
    <BrowserRouter>
      <Header />
      <div className="header-margin"></div>
      <Routes />
    </BrowserRouter>
  );
}

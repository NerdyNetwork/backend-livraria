import { Routes as ReactRoutes, Route } from "react-router-dom";
import { Home } from "./pages/Home";

export const Routes = () => {
  return (
    <ReactRoutes>
      <Route path="/" element={<Home />} />
    </ReactRoutes>
  );
}

import { Routes as ReactRoutes, Route } from "react-router-dom";
import { Home } from "./pages/Home";
import { BookInfos } from "./pages/BookInfos";
import { Login } from "./pages/Login";
import { Register } from "./pages/Register";

export const Routes = () => {
  return (
    <ReactRoutes>
      <Route path="/" element={<Home />} />
      <Route path="/books/:id" element={<BookInfos />} />
      <Route path="/login" element={<Login />} />
      <Route path="/register" element={<Register />} />
    </ReactRoutes>
  );
}

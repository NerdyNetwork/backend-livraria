import { Routes as ReactRoutes, Route } from "react-router-dom";
import { Home } from "./pages/Home";
import { BookInfos } from "./pages/BookInfos";

export const Routes = () => {
  return (
    <ReactRoutes>
      <Route path="/" element={<Home />} />
      <Route path="/books/:id" element={<BookInfos />} />
    </ReactRoutes>
  );
}

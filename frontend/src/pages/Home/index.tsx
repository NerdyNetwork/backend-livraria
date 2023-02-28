import "./style.scss";
import bookSvg from "../../assets/undraw_books.svg";

export const Home = () => {
  return (
    <div className="container">
      <h1 className="title">Frontend Iniciado</h1>
      <img src={bookSvg} alt="imagem principal" />
    </div>
  );
};

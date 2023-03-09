import "./style.scss";
import bookImage from "../../assets/bookimage.jpeg";
import { Player } from "@lottiefiles/react-lottie-player";

const Container1 = () => (
  <div className="container-1">
    <div className="subcontainer">
      <h1 className="title">Frontend Iniciado</h1>
      <span>
        Lorem ipsum dolor sit amet consectetur adipisicing elit. Dolores ducimus
        repudiandae, pariatur obcaecati officiis deserunt praesentium in
        laudantium quos quasi, harum quidem corporis! Id suscipit mollitia quas,
        quae est commodi!
      </span>
      <div className="search-input">
        <input type="text" placeholder="Pesquisar livro" />
        <button>Pesquisar</button>
      </div>
    </div>
    <Player
      src="https://assets9.lottiefiles.com/packages/lf20_d0gmxgy5KG.json"
      className="player"
      loop
      autoplay
      style={{width:500}}
    />
  </div>
);

const Container2 = () => (
  <div className="container-2">
    <h1 className="title">Livros mais vendidos</h1>
    <div className="subcontainer">
      <img src={bookImage} alt="capa do livro" />
      <img src={bookImage} alt="capa do livro" />
      <img src={bookImage} alt="capa do livro" />
      <img src={bookImage} alt="capa do livro" />
      <img src={bookImage} alt="capa do livro" />
    </div>
  </div>
);

export const Home = () => {
  return (
    <div>
      <div className="infinity-animation">
        <h5>
          Lorem ipsum dolor, sit amet consectetur adipisicing elit. Corrupti
          repudiandae, debitis perspiciatis distinctio dignissimos dolore
          facilis unde, itaque doloremque sed blanditiis eaque molestiae alias
          commodi, consequuntur voluptatem? Recusandae, accusantium ipsam.
        </h5>
      </div>

      <Container1 />
      <Container2 />
    </div>
  );
};

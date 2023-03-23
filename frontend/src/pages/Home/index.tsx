import "./style.scss";
import bookImage from "../../assets/bookimage.jpeg";
import { Player } from "@lottiefiles/react-lottie-player";
import bookanimation from "../../assets/bookanimation.json";

const Container1 = () => (
  <div className="container-1">
    <div className="subcontainer">
      <h1 className="title">Os melhores livros</h1>
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
      src={bookanimation}
      
      className="player"
      loop
      autoplay
      style={{ width: 550 }}
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
      <div className="infinite-animation">
        <p>
          <span style={{margin: "0 20px"}}>
            Lorem, ipsum dolor sit amet consectetur adipisicing elit. Earum nihil, adipisci, quasi qui explicabo ipsum similique molestias nobis, atque dolore nostrum velit impedit doloremque aut cumque recusandae fugit perspiciatis repudiandae.
          </span>
          Lorem, ipsum dolor sit amet consectetur adipisicing elit. Earum nihil, adipisci, quasi qui explicabo ipsum similique molestias nobis, atque dolore nostrum velit impedit doloremque aut cumque recusandae fugit perspiciatis repudiandae.
        </p>
      </div>

      <Container1 />
      <Container2 />
    </div>
  );
};

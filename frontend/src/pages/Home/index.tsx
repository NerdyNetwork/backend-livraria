import styles from "./styles.module.scss";
import bookImage from "../../assets/bookimage.jpeg";
import { Player } from "@lottiefiles/react-lottie-player";
import { Header } from "../../components/Header";

export const Home = () => {
  return (
    <div>
      <Header />

      <div className={styles["container-1"]}>
        <div className={styles.subcontainer}>
          <h1 className={styles.title}>Os melhores livros</h1>
          <span>Lorem ipsum dolor sit amet consectetur adipisicing elit. Culpa ex quae dolore rem libero quibusdam laboriosam iste.</span>
          <div className={styles["search-input"]}>
            <input type="text" placeholder="Pesquisar livro" />
            <button>Pesquisar</button>
          </div>
        </div>
        <Player
          src="https://assets2.lottiefiles.com/packages/lf20_cpeiwzvb.json"
          loop
          autoplay
        />
      </div>

      <div className={styles["infinite-animation"]}>
        <p>
          <span style={{ margin: "0 20px" }}>
            Lorem, ipsum dolor sit amet consectetur adipisicing elit. Earum
            nihil, adipisci, quasi qui explicabo ipsum similique molestias
            nobis, atque dolore nostrum velit impedit doloremque aut cumque
            recusandae fugit perspiciatis repudiandae.
          </span>
          Lorem, ipsum dolor sit amet consectetur adipisicing elit. Earum nihil,
          adipisci, quasi qui explicabo ipsum similique molestias nobis, atque
          dolore nostrum velit impedit doloremque aut cumque recusandae fugit
          perspiciatis repudiandae.
        </p>
      </div>

      <div className={styles["container-2"]}>
        <h1 className={styles.title}>Livros mais vendidos</h1>
        <div className={styles.subcontainer}>
          <img src={bookImage} alt="capa do livro" />
          <img src={bookImage} alt="capa do livro" />
          <img src={bookImage} alt="capa do livro" />
          <img src={bookImage} alt="capa do livro" />
          <img src={bookImage} alt="capa do livro" />
        </div>
      </div>
    </div>
  );
};

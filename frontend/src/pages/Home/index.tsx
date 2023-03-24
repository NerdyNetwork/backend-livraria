import styles from "./styles.module.scss";
import bookImage from "../../assets/bookimage.jpeg";
import { Player } from "@lottiefiles/react-lottie-player";
import { Header } from "../../components/Header";

export const Home = () => {
  const spamPhrases = [
    "O sucesso em forma de livro!",
    "Os melhores livros para enriquecer sua mente!",
    "Os mais vendidos e amados, agora ao seu alcance!",
    "A porta de entrada para um mundo de conhecimento!",
    "Best sellers para alimentar sua mente!",
    "Descubra histórias incríveis nos melhores livros!",
    "Descubra por que esses livros são os mais vendidos!",
    "A leitura que transforma a sua vida!",
    "Os best sellers que não podem faltar na sua estante!",
    "Leia os melhores livros e amplie seu universo!",
    "Os livros mais populares estão aqui!",
    "Sua dose diária de inspiração está nos melhores livros!",
    "Best sellers para todas as idades e gostos!",
    "Aqui, você encontra os melhores livros para transformar seu dia!",
    "Os livros que estão conquistando o mundo!",
    "Os melhores livros, a chave para um mundo mais rico!",
    "Leia os best sellers e faça parte da história!",
    "Leia os melhores livros e viva novas aventuras a cada página!",
    "Aqui você encontra os best sellers que vão te surpreender!",
    "Os melhores livros, uma viagem sem sair do lugar!",
  ];

  return (
    <div>
      <Header />

      <div className={styles["container-1"]}>
        <div className={styles.subcontainer}>
          <p className={styles.title}>Descubra novas histórias!</p>
          <span>
            Abra as portas da sua imaginação e mergulhe no mundo dos
            livros conosco, abra um livro e comece uma aventura!
          </span>
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
          <span>
            {spamPhrases.map(v => <span style={{marginRight: 10}}>{v}</span>)}
          </span>
          <span>
            {spamPhrases.map(v => <span style={{marginRight: 10}}>{v}</span>)}
          </span>
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

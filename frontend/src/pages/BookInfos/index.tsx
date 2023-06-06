import { useState } from "react";
import styles from "./styles.module.scss";
import bookImage from "../../assets/bookimage.jpeg";

export const BookInfos = () => {
  const [amount, setAmount] = useState(1);

  const specifications = {
    keys: [
    "Ano da Edição",
    "Autor",
    "Editora",
    "Idioma",
    "Número de Páginas",
    "Acabamento",
    "Título Original",
    "Tradutor",
    "ISBN",
    "Subtitulo",
    ],
    values: [
      "2017",
      "Dweck, Carol S.",
      "Editora Schwarcz SA",
      "Português",
      "312",
      "Livro brochura (paperback)",
      "Mindset",
      "Duarte. S.",
      "978-85-470-0024-0",
      "A nova psicologia do sucesso",
    ]
  };

  return (
    <div className={styles.container}>
      <div className={styles.subcontainer}>
        <div id={styles["book-container"]}>
          <img src={bookImage} alt="capa do livro" />
        </div>
        <div id={styles["menu-sale"]}>
          <div className={styles["box-shadow-menu"]}>
            <h3 className="title-component">Mindset</h3>
            <p>Objetiva</p>
            <hr />
            <div id={styles["price-amount"]}>
              <span id={styles.price}>R$ 35,90</span>
              <div>
                <button onClick={() => setAmount(Math.max(1, amount - 1))}>
                  -
                </button>
                <input
                  min={1}
                  max={999}
                  type="number"
                  value={amount}
                  onChange={(ev) =>
                    setAmount(
                      Math.min(999, Math.max(1, parseInt(ev.target.value)))
                    )
                  }
                />
                <button onClick={() => setAmount(Math.min(999, amount + 1))}>
                  +
                </button>
              </div>
            </div>
            <div className={styles["buttons-container"]}>
              <button>Adicionar ao carrinho</button>
              <button>Comprar</button>
            </div>
            <div id={styles["cep-info"]}>
              <hr />
              <h4>Onde você está?</h4>
              <p>
                Informe sua localização para calcularmos os valores de frete e
                localizarmos possíveis pontos de retirada.
              </p>
              <input type="number" placeholder="Digite o seu CEP" />
            </div>
          </div>
        </div>
      </div>

      <section id={styles.description}>
        <h1 className="title-component">Descrição</h1>
        <p>
          Carol S. Dweck, professora de psicologia na Universidade Stanford e
          especialista internacional em sucesso e motivação, desenvolveu, ao
          longo de décadas de pesquisa, um conceito fundamental: a atitude
          mental com que encaramos a vida, que ela chama de "mindset", é crucial
          para o sucesso. Dweck revela de forma brilhante como o sucesso pode
          ser alcançado pela maneira como lidamos com nossos objetivos. O
          mindset não é um mero traço de personalidade, é a explicação de por
          que somos otimistas ou pessimistas, bem-sucedidos ou não. Ele define
          nossa relação com o trabalho e com as pessoas e a maneira como
          educamos nossos filhos. É um fator decisivo para que todo o nosso
          potencial seja explorado.
        </p>
      </section>

      <section id={styles.specification}>
        <h1 className="title-component">Especificação</h1>

        <div className={styles.subsection}>
          <div>
            {specifications.keys.map((key => (
              <b>{key}</b>
            )))}
          </div>
          <div>
            {specifications.values.map((value => (
              <span>{value}</span>
            )))}
          </div>
        </div>
      </section>
    </div>
  );
};

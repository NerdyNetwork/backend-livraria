import { useState } from "react";
import styles from "./styles.module.scss";
import bookImage from "../../assets/bookimage.jpeg";

export const BookInfos = () => {
  const [amount, setAmount] = useState(1);

  return (
    <div className={styles.container}>
      <div id={styles["book-container"]}>
        <img src={bookImage} alt="capa do livro" />
      </div>
      <div id={styles["menu-sale"]}>
        <div className={styles["box-shadow-menu"]}>
          <h3>Mindset</h3>
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
  );
};

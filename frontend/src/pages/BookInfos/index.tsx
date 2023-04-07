import { useState } from "react";
import styles from "./styles.module.scss";
import bookImage from "../../assets/bookimage.jpeg";

export const BookInfos = () => {
  const [amount, setAmount] = useState(1);
  
  return (
    <div className={styles.container}>
      <div className={styles.book}>
        <img
          src={bookImage}
          alt="capa do livro"
        />
      </div>
      <div className={styles["menu-sale"]}>
        <h3>Mindset</h3>
        <p>Objetiva</p>
        <hr />
        <div className={styles["price-amount"]}>
          <span>R$ 35,90</span>
          <div>
            <button onClick={() => setAmount(Math.max(1, amount - 1))}>-</button>
            <input min={1} type="number" value={amount} onChange={ev => setAmount(parseInt(ev.target.value))} />
            <button onClick={() => setAmount(amount + 1)}>+</button>
          </div>
        </div>
        <div className={styles.buttons}>
          <button className={styles.button}>Adicionar ao carrinho</button>
          <button className={styles.button}>Comprar</button>
        </div>
      </div>
    </div>
  );
};

import styles from "./styles.module.scss";
import { MenuIcon } from "../MenuIcon";
import { Link } from "react-router-dom";

export const Header = () => {
  return (
    <div className={styles.container}>
      <div>
        <Link to="/" className={styles.title}>BOOKSTORE</Link>

        <MenuIcon />

        <span className={styles["menu-label"]}>Busque por categoria</span>
      </div>
      <div>
        <p>Meus Pedidos</p>
        <p>Entrar</p>
        <p>Minha cesta</p>
      </div>
    </div>
  );
};

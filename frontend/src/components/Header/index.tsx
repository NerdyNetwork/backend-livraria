import styles from "./styles.module.scss";
import "../../sass/components.scss";
import { MenuIcon } from "../MenuIcon";

export const Header = () => {
  return (
    <div className={styles.container}>
      <div>
        <p className="title" style={{ fontSize: "1.5rem", color: "var(--white)" }}>BOOKSTORE</p>

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

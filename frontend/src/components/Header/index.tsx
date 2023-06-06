import styles from "./styles.module.scss";
import { MenuIcon } from "../MenuIcon";
import { Link, useNavigate } from "react-router-dom";

export const Header = () => {
  const navigate = useNavigate();

  return (
    <div className={styles.container}>
      <div>
        <Link to="/" className={styles.title}>BOOKSTORE</Link>

        <MenuIcon />

        <span className={styles["menu-label"]}>Busque por categoria</span>
      </div>
      <div>
        <b>Meus Pedidos</b>
        <b onClick={() => navigate("/login")}>Entrar</b>
        <b onClick={() => navigate("/register")}>Cadastrar</b>
        <b>Minha cesta</b>
      </div>
    </div>
  );
};

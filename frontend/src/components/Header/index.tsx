import { useState } from "react";
import styles from "./styles.module.scss";
import { MenuIcon } from "../MenuIcon";
import { Link, useNavigate } from "react-router-dom";
import { Modal } from "../Modal";

export const Header = () => {
  const navigate = useNavigate();

  const [isModalOpen, setIsModalOpen] = useState(false);

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
        <b onClick={() => setIsModalOpen(true)}>Minha cesta</b>
      </div>

      {
        isModalOpen &&
        <Modal onClose={() => setIsModalOpen(false)}>

        </Modal>
      }
    </div>
  );
};

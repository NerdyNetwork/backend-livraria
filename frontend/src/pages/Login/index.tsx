import styles from "./styles.module.scss";

export const Login = () => {
  return (
    <div className={styles.container}>
      <div className={styles.subcontainer}>
        <h1 className="title-component" style={{textAlign: "center", marginBottom: 20}}>Login do cliente</h1>
        <label>Email</label>
        <input placeholder="Insira aqui seu email" type="email" />
        <label>Senha</label>
        <input placeholder="Insira aqui sua senha" type="password" />
        <button>Entrar</button>
      </div>
    </div>
  );
};

import styles from "../Login/styles.module.scss";

export const Register = () => {
  return (
    <div className={styles.container}>
      <div className={styles.subcontainer}>
        <h1 className="title-component" style={{textAlign: "center", marginBottom: 20}}>Cadastro do cliente</h1>
        <label>Email</label>
        <input placeholder="Insira aqui seu email" type="email" />
        <label>Senha</label>
        <input placeholder="Insira aqui sua senha" type="password" />
        <label>Confirme sua senha</label>
        <input placeholder="Comfirme sua senha" type="password" />
        <button>Entrar</button>
      </div>
    </div>
  );
};

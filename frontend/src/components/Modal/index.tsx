import { ReactNode } from 'react';

import styles from './styles.module.scss';

type ModalProps = {
  onClose: () => void;
  children: ReactNode;
}

export const Modal = ({ onClose, children }: ModalProps) => {
  const closeModal = () => {
    onClose();
  };

  return (
    <div className={styles["modal"]}>
      <div className={styles["modal-content"]}>
        <div className={styles["modal-content-column"]}>
          {children}
        </div>
        
        <div className={styles["header-content"]}>
          <button className={styles["close-button"]} onClick={closeModal}>
            X
          </button>
        </div>
      </div>
    </div>
  );
};

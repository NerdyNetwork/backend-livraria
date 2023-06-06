import React, { useState } from 'react';
import styles from './styles.module.scss';

interface ModalProps {
  onClose: () => void;
  children: React.ReactNode;
}

export const Modal: React.FC<ModalProps> = ({ onClose, children }) => {
  const closeModal = () => {
    onClose();
  };

  return (
    <div className={styles["modal"]}>
      <div className={styles["modal-content"]}>
        <div className={styles["header"]}>
          <button className={styles["close-button"]} onClick={closeModal}>
            X
          </button>
        </div>
        {children}
      </div>
    </div>
  );
};

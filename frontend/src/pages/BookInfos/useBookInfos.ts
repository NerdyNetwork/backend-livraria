import { useState } from 'react';
import { Cart } from "../../types/Cart";
import bookImage from "../../assets/bookimage.jpeg";

export const useBookInfos = () => {
  const [amount, setAmount] = useState(1);

  const data = {
    id: "1",
    specifications: {
      keys: [
        "Ano da Edição",
        "Autor",
        "Editora",
        "Idioma",
        "Número de Páginas",
        "Acabamento",
        "Título Original",
        "Tradutor",
        "ISBN",
        "Subtitulo",
      ],
      values: [
        "2017",
        "Dweck, Carol S.",
        "Editora Schwarcz SA",
        "Português",
        "312",
        "Livro brochura (paperback)",
        "Mindset",
        "Duarte. S.",
        "978-85-470-0024-0",
        "A nova psicologia do sucesso",
      ],
    },
  };

  const handleAddToCart = () => {
    let currentCart: Cart[] = JSON.parse(localStorage.getItem("cart") ?? "[]");
    let bookIndexOnCart = currentCart.findIndex((v) => v.id === data.id);
    if (bookIndexOnCart == -1) {
      currentCart.push({
        id: "1",
        name: "Mindset",
        img: bookImage,
        amount,
        price: 35.9,
      });
    } else {
      currentCart[bookIndexOnCart].amount += amount;
    }
    localStorage.setItem("cart", JSON.stringify(currentCart));
  };

  return {
    amount, setAmount, data, handleAddToCart, bookImage
  }
}
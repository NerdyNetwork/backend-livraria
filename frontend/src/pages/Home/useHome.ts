import { useNavigate } from 'react-router-dom';
import bookImage from "../../assets/bookimage.jpeg";

export const useHome = () => {
  
  const navigate = useNavigate();

  const books = [
    {
      id: 1,
      alt: "capa do livro",
      image: bookImage,
    },
    {
      id: 1,
      alt: "capa do livro",
      image: bookImage,
    },
    {
      id: 1,
      alt: "capa do livro",
      image: bookImage,
    },
    {
      id: 1,
      alt: "capa do livro",
      image: bookImage,
    },
    {
      id: 1,
      alt: "capa do livro",
      image: bookImage,
    }
  ]

  const spamPhrases = [
    "O sucesso em forma de livro!",
    "Os melhores livros para enriquecer sua mente!",
    "Os mais vendidos e amados, agora ao seu alcance!",
    "A porta de entrada para um mundo de conhecimento!",
    "Best sellers para alimentar sua mente!",
    "Descubra histórias incríveis nos melhores livros!",
    "Descubra por que esses livros são os mais vendidos!",
    "A leitura que transforma a sua vida!",
    "Os best sellers que não podem faltar na sua estante!",
    "Leia os melhores livros e amplie seu universo!",
    "Os livros mais populares estão aqui!",
    "Sua dose diária de inspiração está nos melhores livros!",
    "Best sellers para todas as idades e gostos!",
    "Aqui, você encontra os melhores livros para transformar seu dia!",
    "Os livros que estão conquistando o mundo!",
    "Os melhores livros, a chave para um mundo mais rico!",
    "Leia os best sellers e faça parte da história!",
    "Leia os melhores livros e viva novas aventuras a cada página!",
    "Aqui você encontra os best sellers que vão te surpreender!",
    "Os melhores livros, uma viagem sem sair do lugar!",
  ];

  return {
    navigate,
    spamPhrases,
    books
  }
}
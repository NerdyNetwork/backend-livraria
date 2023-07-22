import { useState } from "react";
import { useNavigate } from "react-router-dom";

export const useHeader = () => {
  const navigate = useNavigate();

  const [isModalOpen, setIsModalOpen] = useState(false);

  
  return {
    navigate, 
    isModalOpen, 
    setIsModalOpen
  }
}
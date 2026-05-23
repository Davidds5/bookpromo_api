package com.davidds5.bookpromo.service;

import com.davidds5.bookpromo.dto.PriceAlertRequestDTO;
import com.davidds5.bookpromo.dto.PriceAlertResponseDTO;
import com.davidds5.bookpromo.entity.Book;
import com.davidds5.bookpromo.entity.PriceAlert;
import com.davidds5.bookpromo.entity.User;
import com.davidds5.bookpromo.repository.BookRepository;
import com.davidds5.bookpromo.repository.PriceAlertRepository;
import com.davidds5.bookpromo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceAlertService {

    @Autowired
    private PriceAlertRepository priceAlertRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    public PriceAlertResponseDTO save(PriceAlertRequestDTO dto) {
        Book book = bookRepository.findById(dto.getBookId())
                .orElseThrow(() -> new IllegalArgumentException("Nao e possivel criar um alerta: Livro com ID " + dto.getBookId() + " nao encotrado"));

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Nao foi possivel criar um alerta: User com ID " + dto.getUserId() + " nao encotrado"));

        PriceAlert savePriceAlert = new PriceAlert();
        savePriceAlert.setBook(book);
        savePriceAlert.setUser(user);
        savePriceAlert.setDesiredPrice(dto.getDesiredPrice());
        savePriceAlert.setActive(true);

        PriceAlert priceAlert = priceAlertRepository.save(savePriceAlert);
        return PriceAlertResponseDTO.fromEntity(priceAlert);
    }

    public List<PriceAlertResponseDTO> findAll() {
      return priceAlertRepository.findAll()
                .stream()
                .map(PriceAlertResponseDTO::fromEntity)
                .toList();

    }

    public void delete(long id) {
        if (!priceAlertRepository.existsById(id)) {
            throw new IllegalArgumentException("Nao foi possivel deleta: Alerta de preco com ID " + id + " nao encontrado");
        }
        priceAlertRepository.deleteById(id);

    }
}

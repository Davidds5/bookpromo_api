package com.davidds5.bookpromo.service;

import com.davidds5.bookpromo.dto.PriceAlertRequestDTO;
import com.davidds5.bookpromo.dto.PriceAlertResponseDTO;
import com.davidds5.bookpromo.entity.Book;
import com.davidds5.bookpromo.entity.PriceAlert;
import com.davidds5.bookpromo.entity.User;
import com.davidds5.bookpromo.repository.BookRepository;
import com.davidds5.bookpromo.repository.PriceAlertRepository;
import com.davidds5.bookpromo.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PriceAlertService {

    private final PriceAlertRepository priceAlertRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public PriceAlertResponseDTO save(PriceAlertRequestDTO dto) {
        Book book = bookRepository.findById(dto.getBookId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Nao e possivel criar um alerta: Livro com ID " + dto.getBookId() + " nao encotrado"));

        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Nao foi possivel criar um alerta: User com ID " + dto.getUserId() + " nao encotrado"));

        PriceAlert savePriceAlert = new PriceAlert();
        savePriceAlert.setBookId(book);
        savePriceAlert.setUserId(user);
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
            throw new IllegalArgumentException(
                    "Nao foi possivel deleta: Alerta de preco com ID " + id + " nao encontrado");
        }
        priceAlertRepository.deleteById(id);

    }


    public PriceAlertResponseDTO getPriceAlertById(long id) {
        return priceAlertRepository.findById(id)
                .map(PriceAlertResponseDTO :: fromEntity)
                .orElseThrow(() ->new IllegalArgumentException("Alerta de preco com ID " + id + " nao encontrado"));
    }


    public PriceAlertResponseDTO update(Long id, PriceAlertRequestDTO dto) {
        PriceAlert priceAlert = priceAlertRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Alerta de preco com ID " + id + " nao encontrado"));

        Book book = bookRepository.findById(dto.getBookId())
        .orElseThrow(() -> new IllegalArgumentException("Livro com ID " + dto.getBookId() + " nao encontrado"));

        User user = userRepository.findById(dto.getUserId())
        .orElseThrow(() -> new IllegalArgumentException("Usuario com ID " + dto.getUserId() + " nao encontrado"));

        priceAlert.setBookId(book);
        priceAlert.setUserId(user);
        priceAlert.setDesiredPrice(dto.getDesiredPrice());

        return PriceAlertResponseDTO.fromEntity(priceAlertRepository.save(priceAlert));
    }

}

package hu.eke.multiparadigmasprogramozasinyelvekbeadando.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MultiParadigmasProgramozasiNyelvekBeadandoRequest {
    private List<String> addresses;
}
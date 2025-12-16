package pbd.ponto_eletronico.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pbd.ponto_eletronico.repository.SeqCodeRepository;

@Service
@RequiredArgsConstructor
public class SeqCodeService {
    private final SeqCodeRepository seqCodeRepository;

    public  Long getSeqCode() {
        return seqCodeRepository.nextSeqCode();
    }
}

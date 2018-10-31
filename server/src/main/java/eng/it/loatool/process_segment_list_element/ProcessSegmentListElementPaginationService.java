package eng.it.loatool.process_segment_list_element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProcessSegmentListElementPaginationService {

    public Iterable getAll(int page, int size) {
        if (page < 0 || size < 1) {
            return processSegmentListElementRepository.findAll(sort);
        }
        return processSegmentListElementRepository.findAll(PageRequest.of(page, size, sort));
    }

    public Iterable getAllLike(String namePiece, int page, int size) {
        if (page < 0 || size < 1) {
            return processSegmentListElementRepository.getProcessSegmentListElementsLike(namePiece);
        }
        return processSegmentListElementRepository.getProcessSegmentListElementsLike(namePiece, PageRequest.of(page, size, sort));
    }

    @Autowired private ProcessSegmentListElementRepository processSegmentListElementRepository;
    private Sort sort = Sort.by(
        Sort.Order.asc("mainProcess"),
        Sort.Order.asc("subProcessLevel1"),
        Sort.Order.asc("subProcessLevel2"),
        Sort.Order.asc("subProcessLevel3"),
        Sort.Order.asc("subProcessLevel4"),
        Sort.Order.asc("subProcessLevel5")
    );

}

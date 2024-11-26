package br.com.aceleraprogramador.gerenciamento_pedidos.utils;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import java.util.Objects;

@UtilityClass
public class PaginacaoUtils {

    public static Pageable criarPageable(Integer pageNumber, Integer pageSize, String sortBy, String sortDirection) {
        if (Objects.isNull(pageNumber)) {
            pageNumber = 0;
        }
        if (Objects.isNull(pageSize)) {
            pageSize = 10;
        }
        if (StringUtils.isEmpty(sortBy)) {
            sortBy = "id";
        }
        if (StringUtils.isEmpty(sortDirection)) {
            sortDirection = "ASC";
        }
        Sort sort = "DESC".equalsIgnoreCase(sortDirection) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        return PageRequest.of(pageNumber, pageSize, sort);
    }
}

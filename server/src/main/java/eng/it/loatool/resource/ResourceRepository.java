package eng.it.loatool.resource;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRepository extends PagingAndSortingRepository<Resource, Integer> {

    final static String getResourceItemsBySubprocessIdQuery = (
        " select new eng.it.loatool.resource.ResourceListItem(" +
        "    resource," +
        "    cast( coalesce(sign(subProcessLevelResource.pkTbId),0) as boolean)," +
        "    subProcessLevelResource.pkTbId" +
        " )" +
        " from" +
        "    Resource resource" +
        " left join" +
        "    SubProcessLevelResource subProcessLevelResource" +
        " on" +
        "    subProcessLevelResource.resource.pkTbId=resource.pkTbId and" +
        "    subProcessLevelResource.subprocessLevel.pkTbId=:subprocessLevelId"
    );

    final static String getResourceItemsBySubprocessIdAndResourceSearchTermQuery = (
        getResourceItemsBySubprocessIdQuery + " where lower(resource.name) like lower(concat('%', :namePiece,'%'))"
    );

    @Query(getResourceItemsBySubprocessIdQuery)
    Page<ResourceListItem> getResourceItemsBySubprocessId(@Param("subprocessLevelId") Integer subprocessLevelId, Pageable pageable);

    @Query(getResourceItemsBySubprocessIdQuery)
    Iterable<ResourceListItem> getResourceItemsBySubprocessId(@Param("subprocessLevelId") Integer subprocessLevelId);

    @Query(getResourceItemsBySubprocessIdAndResourceSearchTermQuery)
    Page<ResourceListItem> getResourceItemsBySubprocessIdAndResourceSearchTerm(
        @Param("subprocessLevelId") Integer subprocessLevelId,
        @Param("namePiece") String namePiece,
        Pageable pageable
    );

    @Query(getResourceItemsBySubprocessIdAndResourceSearchTermQuery)
    Iterable<ResourceListItem> getResourceItemsBySubprocessIdAndResourceSearchTerm(
        @Param("subprocessLevelId") Integer subprocessLevelId,
        @Param("namePiece") String namePiece
    );

    @Query("from Resource where name = :name")
    public Optional<Resource> getResourceByName(@Param("name") String name);

}

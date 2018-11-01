package eng.it.loatool.resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRepository extends PagingAndSortingRepository<Resource, Integer> {

    @Query(
        " select new eng.it.loatool.resource.ResourceListItem(" +
        "    resource," +
        "    cast( coalesce(sign(subProcessLevelResource.pkTbId),0) as boolean)" +
        " )" +
        " from" +
        "    Resource resource" +
        " left join" +
        "    SubProcessLevelResource subProcessLevelResource" +
        " on" +
        "    subProcessLevelResource.resource.pkTbId=resource.pkTbId and" +
        "    subProcessLevelResource.subprocessLevel.pkTbId=:subprocessLevelId"
    )
    Page<ResourceListItem> getResourceItemsBySubprocessId(@Param("subprocessLevelId") Integer subprocessLevelId, Pageable pageable);

    @Query(
        " select new eng.it.loatool.resource.ResourceListItem(" +
        "    resource," +
        "    cast( coalesce(sign(subProcessLevelResource.pkTbId),0) as boolean)" +
        " )" +
        " from" +
        "    Resource resource" +
        " left join" +
        "    SubProcessLevelResource subProcessLevelResource" +
        " on" +
        "    subProcessLevelResource.resource.pkTbId=resource.pkTbId and" +
        "    subProcessLevelResource.subprocessLevel.pkTbId=:subprocessLevelId"
    )
    Iterable<ResourceListItem> getResourceItemsBySubprocessId(@Param("subprocessLevelId") Integer subprocessLevelId);

}

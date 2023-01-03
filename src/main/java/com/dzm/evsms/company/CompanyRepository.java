package com.dzm.evsms.company;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends CrudRepository<Company, Long> {

	@Query(
			value = "WITH RECURSIVE tree AS (\r\n"
					+ "  SELECT id, ARRAY[]\\:\\:bigint[] AS ancestors\r\n"
					+ "  FROM Company WHERE parent_id IS NULL\r\n"
					+ "  UNION\r\n"
					+ "  SELECT Company.id, tree.ancestors || Company.parent_id\r\n"
					+ "  FROM Company, tree\r\n"
					+ "  WHERE Company.parent_id = tree.id\r\n"
					+ ") SELECT id FROM tree WHERE :rootCompanyId = ANY(tree.ancestors)"
					+ "UNION\r\n"
					+ "SELECT id from Company WHERE id = :rootCompanyId",
			nativeQuery = true)

	Set<Long> findAllChildCompanies(Long rootCompanyId);


}

package nasdaq.homework.presidentelectionapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import nasdaq.homework.presidentelectionapp.model.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long>
{
}

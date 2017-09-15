package net.blueapple.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Lithium on 9/15/2017.
 */
public interface UserRepository extends PagingAndSortingRepository<User,Long> {

    User findByUsername(String username);
}

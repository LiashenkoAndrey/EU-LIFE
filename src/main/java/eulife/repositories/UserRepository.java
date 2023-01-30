package eulife.repositories;

import eulife.domain.Image;
import eulife.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);

    @Query("select u.user_details.image from User u where u.id = :id")
    Image findImageByUserId(@Param("id") Long id);
}

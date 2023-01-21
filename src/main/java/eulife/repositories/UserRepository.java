package eulife.repositories;

import eulife.domain.Image;
import eulife.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);

    @Query("select u.user_details.image from User u where u.id = :id")
    Image findImageByUserId(@Param("id") Long id);
}

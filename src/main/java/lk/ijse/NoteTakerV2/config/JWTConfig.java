package lk.ijse.NoteTakerV2.config;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.NoteTakerV2.service.JWTService;
import lk.ijse.NoteTakerV2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class JWTConfig extends OncePerRequestFilter {

    private JWTService jwtService;
    private UserService userService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String initToken = request.getHeader("Authorization");
        String userEmial;
        String jwtToken;


        //Initial Validations
        if (StringUtils.isEmpty(initToken) || !initToken.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }

        //Token received
        jwtToken = initToken.substring(7);
        userEmial = jwtService.extractUsername(jwtToken);

        //User Emial Validations
        if (StringUtils.isNotEmpty(userEmial) &&
                SecurityContextHolder.getContext().getAuthentication() == null) {

            var loadedUser =
                    userService.userDetailsService().loadUserByUsername(userEmial);
            if (jwtService.isTokenValid(jwtToken, loadedUser)) {
                SecurityContext emptyContext =
                        SecurityContextHolder.createEmptyContext();
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(loadedUser, null, loadedUser.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetails(request));
                emptyContext.setAuthentication(authToken);
                SecurityContextHolder.setContext(emptyContext);


            }
        }
    }
}

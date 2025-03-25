package br.com.erp.service;


import br.com.erp.bean.Version;
import br.com.erp.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@RequiredArgsConstructor
public class VersionService {

    public static final String NOT_FOUND_MESSAGE = "Versão não encontrada";

    @Value("${HEROKU_BUILD_COMMIT:0.0.0}")
    private String version;

    @Value("${HEROKU_RELEASE_CREATED_AT:unknown}")
    private String release;

    public Version getVersion() {
        return Version
                .builder()
                .gitVersion(version)
                .dateTime(release)
                .build();
    }

    public Version getVersionByGitVersion(@PathVariable(value = "gitVersion") String gitVersion) {

        if(version.equals(gitVersion)) {
            return Version
                    .builder()
                    .gitVersion(version)
                    .dateTime(release)
                    .build();
        }

        throw new NotFoundException(NOT_FOUND_MESSAGE);
    }
}


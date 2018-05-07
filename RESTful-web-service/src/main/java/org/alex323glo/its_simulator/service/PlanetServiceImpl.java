package org.alex323glo.its_simulator.service;

import org.alex323glo.its_simulator.exception.AppException;
import org.alex323glo.its_simulator.exception.ValidationException;
import org.alex323glo.its_simulator.model.game.Planet;
import org.alex323glo.its_simulator.repository.PlanetRepository;
import org.alex323glo.its_simulator.util.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation of PlanetService interface. See more in abstraction.
 *
 * @author Alexey_O
 * @version 0.1
 *
 * @see PlanetService
 */
@Service
public class PlanetServiceImpl implements PlanetService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlanetServiceImpl.class);

    private final Validator validator;
    private final PlanetRepository planetRepository;

    @Autowired
    public PlanetServiceImpl(Validator validator, PlanetRepository planetRepository) {
        this.validator = validator;
        this.planetRepository = planetRepository;
    }

    /**
     * Searches for existent Planet.
     *
     * @param planetName unique and valid Planet name.
     * @return needed Planet, if it was created before, or null, if it wasn't.
     * @throws AppException if System can't carry out this operation in some reasons
     *                      (see more in method's realisation).
     */
    @Transactional
    @Override
    public Planet findPlanet(String planetName) throws AppException {
        LOGGER.info("Trying to find Planet by its name...");

        try {
            validator.validatePlanetName(planetName);
        } catch (ValidationException e) {
            AppException exception = new AppException("Can't find planet by its name. " + e.getMessage());
            LOGGER.error(exception.getMessage(), exception);
            throw exception;
        }

        Planet planet = planetRepository.findByName(planetName);

        LOGGER.info("Successfully found Planet by its name.");
        return planet;
    }

    /**
     * Lists all existent Planets.
     *
     * @return (not null) List of existent Planets, if operation was successful.
     * @throws AppException if System can't carry out this operation in some reasons
     *                      (see more in method's realisation).
     */
    @Transactional
    @Override
    public List<Planet> findAllPlanets() throws AppException {
        LOGGER.info("Trying to list all Planets...");

        List<Planet> planetList = planetRepository.findAll();

        LOGGER.info("Successfully found Planet by its name.");
        return planetList;
    }
}

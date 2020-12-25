package lab1.model;

import lab1.ValidationException;

public class ValidatedVehicle {

    private final Vehicle vehicle;

    public ValidatedVehicle(Vehicle vehicle) throws ValidationException {
        if (vehicle.getName() == null || vehicle.getName().isEmpty()) {
            throw new ValidationException("Invalid name: '" + vehicle.getName() + "'");
        }
        if (vehicle.getEnginePower() <= 0) {
            throw new ValidationException("Invalid engine power: " + vehicle.getEnginePower());
        }
        if (vehicle.getFuelConsumption() <= 0) {
            throw new ValidationException("Invalid fuel consumption: " + vehicle.getFuelConsumption());
        }
        if (vehicle.getType() == null) {
            throw new ValidationException("Invalid type: " + vehicle.getType());
        }
        if (vehicle.getFuelType() == null) {
            throw new ValidationException("Invalid fuel type: " + vehicle.getFuelType());
        }
        validateCoordinates(vehicle.getCoordinates());

        this.vehicle = vehicle;
    }

    private void validateCoordinates(Coordinates coordinates) throws ValidationException {
        if (coordinates == null || coordinates.getY() <= -619) {
            throw new ValidationException("Invalid coordinates: " + coordinates);
        }
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}

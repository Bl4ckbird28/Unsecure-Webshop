package de.fhws.biedermann.webshop.api.states;

import de.fhws.biedermann.webshop.database.DataAccessAdminPanel;
import de.fhws.biedermann.webshop.models.Nletter;
import de.fhws.biedermann.webshop.models.User;
import de.fhws.biedermann.webshop.models.UserVulnerability;
import de.fhws.biedermann.webshop.utils.SecurityBreachDetection;
import de.fhws.biedermann.webshop.utils.VulnerabilityCheck;
import de.fhws.biedermann.webshop.utils.authentication.MyKeyGenerator;
import de.fhws.biedermann.webshop.utils.handler.DataHandler;
import de.fhws.biedermann.webshop.utils.handler.FlawHandler;
import de.fhws.biedermann.webshop.utils.logic.AdminLogic;
import org.apache.commons.lang.StringUtils;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.core.Response;
import java.security.Key;
import java.util.Base64;

public class UserState extends AbstractState
{
	static DataAccessAdminPanel daap = new DataAccessAdminPanel();

	private UserState( AbstractStateBuilder builder )
	{
		super( builder );
	}

	public static Key createNewUser() {
		// toDo if a uuid is given, store this one in the database
		final Key uuid = MyKeyGenerator.getNewKey();
		if (uuid == null) {
			// toDo: handle on frontend
			throw new InternalServerErrorException( "" );
		}
		daap.lookForClient( Base64.getEncoder().encodeToString(uuid.getEncoded() ) );
		return uuid;
	}

	@Override void lookForFlaw( )
	{
		if ( this.modelToWorkWith instanceof User user ){
			if ( SecurityBreachDetection.isInvalidFileFormat( user.getProfilePicture() ) ) {
				FlawHandler.imageWithWrongDataType( uuid );
			}

			if ( StringUtils.isNotEmpty( user.getDescription() ) && VulnerabilityCheck.checkXSS( user.getDescription())) {
				FlawHandler.putXSS(uuid);
			}

			if( this.modelToWorkWith != this.responseBody )
				FlawHandler.sqlInjection( uuid );
		} else if ( (this.modelToWorkWith instanceof Nletter nletter ))
		{
			if(!nletter.getEmail().contains("@")) {
				FlawHandler.emailWithoutAt(uuid);
			}
		}
	}

	public static class Builder extends AbstractStateBuilder{
		@Override
		public AbstractState build(){
			return new UserState( this );
		}
	}
}

package Service_Layer;

import Domain.Store.Product;
import Domain.info.ProductDetails;
import tests.AcceptanceTests.auxiliary.StoreDetails;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import static Service_Layer.guest_accese.guest_accese.*;
import static Service_Layer.member_accese.member_accese.usecase3_1_Logout;
import static Service_Layer.member_accese.member_accese.usecase3_2_OpenStore;
import static Service_Layer.owner_accese.owner_accese.*;

class InitSystemFromFile {
    private static int guest_id;

    private static void parseCommand(String[] command) throws Exception {
        int i = 0;
        switch (command[i++]) {
            case "MakeAdmin":
                Domain.store_System.System.getInstance(command[i++],command[i]);
                break;
            case "Register":
                usecase2_2_guest_register(command[i++], command[i]);
                break;
            case "Login":
                usecase2_3_login(guest_id, command[i++], command[i]);
                break;
            case "Logout":
                usecase3_1_Logout(guest_id);
                break;
            case "OpenStore":
                usecase3_2_OpenStore(guest_id, new StoreDetails(command[i++], command[i]));
                break;
            case "AddProductToStore":
                usecase4_1_1_AddingProdacsToStore(guest_id, command[i++], parseProduct(command, i));
                break;
            case "AppointOwner":
                usecase4_3_appointOwner(guest_id, command[i++], command[i]);
                break;
            case "AppointManager":
                usecase4_5_appointManager(guest_id, command[i++], command[i]);
                break;
            case "ChangeManagerPermissions":
                //TODO
                break;
            default:
                System.out.println("ignored: " + Arrays.toString(command));
        }

    }

    private static ProductDetails parseProduct(String[] command, int i) {
        return null;    //TODO
    }

    public static void init(String file) {
        String separator = ",";
        String line = "";
        guest_id = ImNew();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((line = br.readLine()) != null) {
                parseCommand(line.split(separator));
            }
        } catch (IOException e) {
            System.err.println("file not found or in wrong format");
            // TODO: error logger
        } catch (Exception e) {
            System.err.println("file in wrong format");
        }
    }
}

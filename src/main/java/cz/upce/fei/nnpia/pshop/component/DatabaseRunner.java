package cz.upce.fei.nnpia.pshop.component;

import cz.upce.fei.nnpia.pshop.entity.Cart;
import cz.upce.fei.nnpia.pshop.entity.Role;
import cz.upce.fei.nnpia.pshop.entity.User;
import cz.upce.fei.nnpia.pshop.entity.enums.*;
import cz.upce.fei.nnpia.pshop.entity.items.Camera;
import cz.upce.fei.nnpia.pshop.entity.items.Lens;
import cz.upce.fei.nnpia.pshop.repository.CartRepository;
import cz.upce.fei.nnpia.pshop.repository.RoleRepository;
import cz.upce.fei.nnpia.pshop.repository.UserRepository;
import cz.upce.fei.nnpia.pshop.repository.items.CameraRepository;
import cz.upce.fei.nnpia.pshop.repository.items.LensRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@AllArgsConstructor
public class DatabaseRunner implements ApplicationRunner {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final CartRepository cartRepository;

    private final CameraRepository cameraRepository;

    private final LensRepository lensRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) {
        roles();
        users();
        cameras();
        lenses();
    }

    private void roles() {
        roleRepository.save(Role.builder().name(RoleE.ADMIN).build());
        roleRepository.save(Role.builder().name(RoleE.USER).build());
    }

    private void users() {
        User user = User.builder()
                        .firstname("Jan")
                        .lastname("Stehno")
                        .email("honzastehno@email.cz")
                        .username("janstehno")
                        .password(passwordEncoder.encode("password"))
                        .roles(Set.of(roleRepository.findByName(RoleE.USER).orElseThrow()))
                        .build();
        userRepository.save(user);
        cartRepository.save(Cart.builder().user(user).build());
    }

    private void cameras() {
        cameraRepository.save(Camera.builder()
                                    .name("Canon EOS R7")
                                    .brand(BrandE.CANON)
                                    .price(36490.0)
                                    .inStorage(2)
                                    .type(CameraE.MIRRORLESS)
                                    .mount_type(MountE.CANON_RF)
                                    .sensor_type(SensorE.APS_C)
                                    .weight(559.0)
                                    .minimum_iso(100)
                                    .maximum_iso(32000)
                                    .af_points(651)
                                    .fps(30.0)
                                    .resolution_width(6960)
                                    .resolution_height(4640)
                                    .sensor_resolution_width(22.3)
                                    .sensor_resolution_height(14.8)
                                    .touch_screen(true)
                                    .rotatable_screen(true)
                                    .thumbnail("cameras/r7.png")
                                    .build());
        cameraRepository.save(Camera.builder()
                                    .name("Canon EOS R8")
                                    .brand(BrandE.CANON)
                                    .price(39490.0)
                                    .inStorage(7)
                                    .type(CameraE.MIRRORLESS)
                                    .mount_type(MountE.CANON_RF)
                                    .sensor_type(SensorE.FULLFRAME)
                                    .weight(461.0)
                                    .minimum_iso(100)
                                    .maximum_iso(102400)
                                    .af_points(4897)
                                    .fps(6.0)
                                    .resolution_width(6000)
                                    .resolution_height(4000)
                                    .sensor_resolution_width(35.9)
                                    .sensor_resolution_height(23.9)
                                    .touch_screen(true)
                                    .rotatable_screen(true)
                                    .thumbnail("cameras/r8.png")
                                    .build());
        cameraRepository.save(Camera.builder()
                                    .name("Canon EOS R3")
                                    .brand(BrandE.CANON)
                                    .price(129990.0)
                                    .inStorage(0)
                                    .type(CameraE.MIRRORLESS)
                                    .mount_type(MountE.CANON_RF)
                                    .sensor_type(SensorE.FULLFRAME)
                                    .weight(822.0)
                                    .minimum_iso(100)
                                    .maximum_iso(102400)
                                    .af_points(1053)
                                    .fps(30.0)
                                    .resolution_width(6000)
                                    .resolution_height(4000)
                                    .sensor_resolution_width(36.0)
                                    .sensor_resolution_height(24.0)
                                    .touch_screen(true)
                                    .rotatable_screen(true)
                                    .thumbnail("cameras/r3.png")
                                    .build());
        cameraRepository.save(Camera.builder()
                                    .name("Nikon Z5")
                                    .brand(BrandE.NIKON)
                                    .price(36990.0)
                                    .inStorage(1)
                                    .type(CameraE.MIRRORLESS)
                                    .mount_type(MountE.NIKON_Z)
                                    .sensor_type(SensorE.FULLFRAME)
                                    .weight(590.0)
                                    .minimum_iso(100)
                                    .maximum_iso(102400)
                                    .af_points(273)
                                    .fps(4.5)
                                    .resolution_width(6016)
                                    .resolution_height(4016)
                                    .sensor_resolution_width(35.9)
                                    .sensor_resolution_height(23.9)
                                    .touch_screen(true)
                                    .rotatable_screen(true)
                                    .thumbnail("cameras/z5.png")
                                    .build());
        cameraRepository.save(Camera.builder()
                                    .name("Nikon Z50")
                                    .brand(BrandE.NIKON)
                                    .price(24490.0)
                                    .inStorage(2)
                                    .type(CameraE.MIRRORLESS)
                                    .mount_type(MountE.NIKON_Z)
                                    .sensor_type(SensorE.APS_C)
                                    .weight(450.0)
                                    .minimum_iso(100)
                                    .maximum_iso(51200)
                                    .af_points(209)
                                    .fps(11.0)
                                    .resolution_width(5568)
                                    .resolution_height(3712)
                                    .sensor_resolution_width(33.5)
                                    .sensor_resolution_height(15.7)
                                    .touch_screen(true)
                                    .rotatable_screen(true)
                                    .thumbnail("cameras/z50.png")
                                    .build());
        cameraRepository.save(Camera.builder()
                                    .name("Canon EOS 5D Mark IV")
                                    .brand(BrandE.CANON)
                                    .price(71990.0)
                                    .inStorage(2)
                                    .type(CameraE.MIRROR)
                                    .mount_type(MountE.CANON_EF)
                                    .sensor_type(SensorE.FULLFRAME)
                                    .weight(800.0)
                                    .minimum_iso(100)
                                    .maximum_iso(32000)
                                    .af_points(61)
                                    .fps(7.0)
                                    .resolution_width(6720)
                                    .resolution_height(4480)
                                    .sensor_resolution_width(36.0)
                                    .sensor_resolution_height(24.0)
                                    .touch_screen(true)
                                    .rotatable_screen(true)
                                    .thumbnail("cameras/5dmarkIV.png")
                                    .build());
        cameraRepository.save(Camera.builder()
                                    .name("Canon EOS 90D")
                                    .brand(BrandE.CANON)
                                    .price(32490.0)
                                    .inStorage(4)
                                    .type(CameraE.MIRROR)
                                    .mount_type(MountE.CANON_EFS)
                                    .sensor_type(SensorE.APS_C)
                                    .weight(701.0)
                                    .minimum_iso(100)
                                    .maximum_iso(25600)
                                    .af_points(45)
                                    .fps(10.0)
                                    .resolution_width(6960)
                                    .resolution_height(4640)
                                    .sensor_resolution_width(22.3)
                                    .sensor_resolution_height(14.8)
                                    .touch_screen(true)
                                    .rotatable_screen(true)
                                    .thumbnail("cameras/90d.png")
                                    .build());
        cameraRepository.save(Camera.builder()
                                    .name("Nikon D850")
                                    .brand(BrandE.NIKON)
                                    .price(78490.0)
                                    .inStorage(1)
                                    .type(CameraE.MIRROR)
                                    .mount_type(MountE.NIKON_F)
                                    .sensor_type(SensorE.FULLFRAME)
                                    .weight(1005.0)
                                    .minimum_iso(100)
                                    .maximum_iso(102400)
                                    .af_points(153)
                                    .fps(7.0)
                                    .resolution_width(8256)
                                    .resolution_height(5504)
                                    .sensor_resolution_width(35.9)
                                    .sensor_resolution_height(23.9)
                                    .touch_screen(true)
                                    .rotatable_screen(true)
                                    .thumbnail("cameras/d850.png")
                                    .build());
        cameraRepository.save(Camera.builder()
                                    .name("Sony Alpha A7 II")
                                    .brand(BrandE.SONY)
                                    .price(31490.0)
                                    .inStorage(2)
                                    .type(CameraE.MIRRORLESS)
                                    .mount_type(MountE.SONY_FE)
                                    .sensor_type(SensorE.FULLFRAME)
                                    .weight(556.0)
                                    .minimum_iso(100)
                                    .maximum_iso(25600)
                                    .af_points(117)
                                    .fps(5.0)
                                    .resolution_width(6000)
                                    .resolution_height(4000)
                                    .sensor_resolution_width(35.8)
                                    .sensor_resolution_height(23.9)
                                    .touch_screen(true)
                                    .rotatable_screen(true)
                                    .thumbnail("cameras/alphaa7II.png")
                                    .build());
    }

    private void lenses() {
        lensRepository.save(Lens.builder()
                                .name("Sigma 35mm f/1.4 DG HSM Art")
                                .brand(BrandE.SIGMA)
                                .price(23290.0)
                                .inStorage(2)
                                .type(LensE.STATIC)
                                .mount_type(MountE.CANON_EF)
                                .min_aperture(1.4)
                                .max_aperture(16.0)
                                .min_focal_length(35.0)
                                .max_focal_length(35.0)
                                .built_in_stabilisation(false)
                                .thumbnail("lenses/sigma35.png")
                                .build());
        lensRepository.save(Lens.builder()
                                .name("Canon RF 50mm f/1.8 STM")
                                .brand(BrandE.CANON)
                                .price(5590.0)
                                .inStorage(9)
                                .type(LensE.STATIC)
                                .mount_type(MountE.CANON_RF)
                                .min_aperture(1.8)
                                .max_aperture(22.0)
                                .min_focal_length(50.0)
                                .max_focal_length(50.0)
                                .built_in_stabilisation(false)
                                .thumbnail("lenses/canon50.png")
                                .build());
        lensRepository.save(Lens.builder()
                                .name("Sony FE 70-200 mm f/2.8 GM II OSS")
                                .brand(BrandE.SONY)
                                .price(16990.0)
                                .inStorage(3)
                                .type(LensE.ZOOM)
                                .mount_type(MountE.SONY_FE)
                                .min_aperture(2.8)
                                .max_aperture(22.0)
                                .min_focal_length(70.0)
                                .max_focal_length(200.0)
                                .built_in_stabilisation(true)
                                .thumbnail("lenses/sony70200.png")
                                .build());
        lensRepository.save(Lens.builder()
                                .name("Nikon Z 50 mm f/1.8 S")
                                .brand(BrandE.NIKON)
                                .price(16990.0)
                                .inStorage(4)
                                .type(LensE.STATIC)
                                .mount_type(MountE.NIKON_Z)
                                .min_aperture(1.8)
                                .max_aperture(16.0)
                                .min_focal_length(50.0)
                                .max_focal_length(50.0)
                                .built_in_stabilisation(false)
                                .thumbnail("lenses/nikon50.png")
                                .build());
        lensRepository.save(Lens.builder()
                                .name("Nikon 10-20 mm f/4.5-5.6 G AF-P VR DX")
                                .brand(BrandE.NIKON)
                                .price(8490.0)
                                .inStorage(3)
                                .type(LensE.ZOOM)
                                .mount_type(MountE.NIKON_F)
                                .min_aperture(4.5)
                                .max_aperture(5.6)
                                .min_focal_length(10.0)
                                .max_focal_length(20.0)
                                .built_in_stabilisation(true)
                                .thumbnail("lenses/nikon1020.png")
                                .build());
    }
}


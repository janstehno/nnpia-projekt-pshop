import Parameter from './parameter.jsx';

function DetailCamera(props) {
    const item = props.item;

    return (
        <div id="detail-bottom">
            <h4>Parametry</h4>
           <Parameter name="Typ fotoaparátu" value={item.type === "MIRRORLESS" ? "Zrcadlovka" : "Bezzrcadlovka"} />
           <Parameter name="Typ bajonetu" value={item.mount_type} />
            <h4>Obrazový snímač</h4>
           <Parameter name="Formát snímače" value={item.sensor_type} />
           <Parameter name="Velikost snímače" value={`${item.sensor_resolution_width} x ${item.sensor_resolution_height}`} />
           <Parameter name="Rozlišení fotografií" value={`${item.resolution_width} x ${item.resolution_height}`} />
            <h4>Ostření</h4>
           <Parameter name="Počet AF bodů" value={item.af_points} />
            <h4>Expozice</h4>
           <Parameter name="ISO - minimální citlivost" value={item.minimum_iso} />
           <Parameter name="ISO - maximální citlivost" value={item.maximum_iso} />
           <Parameter name="Rychlost sériového snímání" value={`${item.fps} sn./s`} />
            <h4>Displej</h4>
           <Parameter name="Dotykový displej" value={item.touch_screen ? "Ano" : "Ne"} />
           <Parameter name="Výklopný displej" value={item.rotatable_screen ? "Ano" : "Ne"} />
           <h4>Fyzikální specifikace</h4>
           <Parameter name="Hmotnost" value={`${item.weight} g`} />
        </div>
    );
}

export default DetailCamera
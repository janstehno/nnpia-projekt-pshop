import Parameter from './parameter.jsx';

function DetailLens(props) {
    const item = props.item;

    return (
        <div id="detail-bottom">
            <h4>Parametry</h4>
            <Parameter name="Typ objektivu" value={item.type === "ZOOM" ? "Zoom" : "Pevný"} />
            <Parameter name="Typ bajonetu" value={item.mount_type} />
            <h4>Světelnost</h4>
            <Parameter name="Minimální ohnisková vzdálenost" value={item.min_aperture} />
            <Parameter name="Maximální ohnisková vzdálenost" value={item.max_aperture} />
            <Parameter name="Světelnost objektivu (nejkratší ohnisko)" value={item.min_focal_length} />
            <Parameter name="Světelnost objektivu (nejdělší ohnisko)" value={item.max_focal_length} />
            <h4>Stabilizace</h4>
            <Parameter name="Stabilizátor obrazu" value={item.build_in_stabilisation ? "Ano" : "Ne"} />
        </div>
    );
}

export default DetailLens
import numpy as np

from py.gno.util.file_management import read_fovea_location_results, read_gt_fovea_location, \
    sort_coordinates_by_filename


def euclidean_distance(gt_coordinates, fovea_coordinates):
    euclidean_distances = np.sqrt(np.sum((gt_coordinates - fovea_coordinates) ** 2, axis=1))

    return euclidean_distances


def evaluate_fovea_location_results(prediction_filename, gt_filename, output_path=None, is_training=False):
    image_filenames, predicted_coordinates = read_fovea_location_results(prediction_filename)
    gt_image_filenames, gt_coordinates = read_gt_fovea_location(gt_filename, is_training)

    gt_coordinates = sort_coordinates_by_filename(image_filenames, gt_image_filenames, gt_coordinates)

    euclidean_distances = euclidean_distance(gt_coordinates, predicted_coordinates)

    mean_euclidean_distances = np.mean(euclidean_distances)
    print('Mean Euclidean distance = {}'.format(str(mean_euclidean_distances)))

    return mean_euclidean_distances
